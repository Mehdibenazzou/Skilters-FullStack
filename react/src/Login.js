import React, {Component} from 'react'
import './Login.css'
import PropTypes from "prop-types";
import { connect } from "react-redux";
import classnames from "classnames";
import { login } from "./Actions/securityActions";

class Login extends Component {

    constructor() {
        super();
        this.state = {
          email: "",
          password: "",
          errors: {}
        };
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    componentDidMount() {
        if (this.props.security.validToken) {
          this.props.history.push("/accueil");
        }
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.security.validToken) {
          this.props.history.push("/accueil");
        }
    
        if (nextProps.errors) {
          this.setState({ errors: nextProps.errors });
        }
    }

    onSubmit(e) {
        e.preventDefault();
        const LoginRequest = {
          email: this.state.email,
          password: this.state.password
        };
    
        this.props.login(LoginRequest);
    }

    onChange(e) {
        this.setState({ [e.target.name]: e.target.value });
    }

    render() {
        const { errors } = this.state;
        return(
            <div className="login">
                <div className="login-triangle"></div>
                
                <p className="login-header"><strong>S</strong>kilters</p>

                <form className="login-container" onSubmit={this.onSubmit}>
                    <p><input name="email" type="email" placeholder="Email" value={this.state.email} onChange={this.onChange} className={classnames({"is-invalid": errors.email})} /></p>
                    <div className="error_display">{errors.email && (<div className="invalid-feedback">{errors.email}</div>)}</div>
                    <p><input name="password" type="password" placeholder="Password" value={this.state.password} onChange={this.onChange} className={classnames({"is-invalid": errors.password})} /></p>
                    <div className="error_display">{errors.password && (<div className="invalid-feedback">{errors.password}</div>)}</div>
                    <p><input type="submit" value="LOGIN"/></p>
                    <p>Vous n'avez pas de <strong>compte</strong>? inscrivez vous <a href="/register">ici</a> !</p>
                </form>
            </div>
        );
    }
}

Login.propTypes = {
    login: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired,
    security: PropTypes.object.isRequired
  };
  
  const mapStateToProps = state => ({
    security: state.security,
    errors: state.errors
  });
  
  export default connect(
    mapStateToProps,
    { login }
  )(Login);
