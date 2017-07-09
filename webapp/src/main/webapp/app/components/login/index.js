'use strict';

import React from 'react';
import PropTypes from 'prop-types';

import {Link} from 'react-router-dom';

import CollapsableAlert from '../common/alert/CollapsableAlert';

import Form from 'react-bootstrap/lib/Form';
import FormGroup from 'react-bootstrap/lib/FormGroup';
import Col from 'react-bootstrap/lib/Col';
import ControlLabel from 'react-bootstrap/lib/ControlLabel';
import FormControl from 'react-bootstrap/lib/FormControl';
import HelpBlock from 'react-bootstrap/lib/HelpBlock';
import Button from 'react-bootstrap/lib/Button';

import getQueryParams from '../../utils/getQueryParams';

import validator from 'validator';
import validateInput from '../../utils/validators/loginValidator'

require('bootstrap/dist/css/bootstrap.css');

class LoginForm extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      username: '',
      password: '',
      errors: {},
      isLoading: false
    };

    this.isValid = this.isValid.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
    this.onChange = this.onChange.bind(this);
    this.clearPasswordInputs = this.clearPasswordInputs.bind(this);
  }

  componentDidMount() {
    const queryParams = getQueryParams(this.context.router.route.location.search);
    const type = queryParams.type;
    const text = queryParams.text;

    if (type !== undefined && !validator.isEmpty(type) && text !== undefined && !validator.isEmpty(text)) {
      this.props.addFlashMessage({type: type, text: text});
    }
  }

  isValid() {
    const {errors, isValid} = validateInput(this.state);

    if (!isValid) {
      this.setState({errors});
    }

    return isValid;
  }

  onSubmit(event) {
    event.preventDefault();

    if (this.isValid()) {
      this.setState({errors: {}, isLoading: true});

      this.props.login(this.state).then(
        response => this.context.router.history.push('/'),
        error => this.setState({errors: {form: error.response.data.message}, isLoading: false})
      );
    }

    this.clearPasswordInputs();
  }

  onChange(event) {
    this.setState({[event.target.name]: event.target.value});
  }

  clearPasswordInputs() {
    this.setState({password: ''});
  }

  render() {
    const isLoading = this.state.isLoading;

    return (
      <Form onSubmit={this.onSubmit} horizontal>
        <CollapsableAlert collapse={!!this.state.errors.form} style='danger' title='Login failed: '
                          message={this.state.errors.form}/>

        <FormGroup controlId='username' validationState={!!this.state.errors.username ? 'error' : null}>
          <Col sm={2}>
            <ControlLabel>Username</ControlLabel>
            <HelpBlock>{this.state.errors.username}</HelpBlock>
          </Col>

          <Col sm={10}>
            <FormControl name='username' type='text' value={this.state.username} onChange={this.onChange}/>
            <FormControl.Feedback/>
          </Col>
        </FormGroup>

        <FormGroup controlId='password' validationState={!!this.state.errors.password ? 'error' : null}>
          <Col sm={2}>
            <ControlLabel>Password</ControlLabel>
            <HelpBlock>{this.state.errors.password}</HelpBlock>
          </Col>

          <Col sm={10}>
            <FormControl name='password' type='password' value={this.state.password} onChange={this.onChange}/>
            <FormControl.Feedback/>
          </Col>
        </FormGroup>

        <Button type='submit' disabled={isLoading}
                onClick={!isLoading ? this.handleClick : null}>{isLoading ? 'Loading...' : 'Sign in'}</Button>

        <Link className='pull-right' to='/login/request-password-reset'>Forgot your password?</Link>
      </Form>
    );
  }
}

LoginForm.propTypes = {
  login: PropTypes.func.isRequired,
  addFlashMessage: PropTypes.func.isRequired
}

LoginForm.contextTypes = {
  router: PropTypes.object.isRequired
}

export default LoginForm;