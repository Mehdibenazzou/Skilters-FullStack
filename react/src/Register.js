import React, {Component} from 'react'
import './Register.css'
import {createNewUser} from './Actions/securityActions'
import PropTypes from 'prop-types'
import {connect} from 'react-redux'
import classnames from "classnames"


class Register extends Component {

    constructor(){
        super();

        this.state = {
            prenom: "",
            nom: "",
            email: "",
            telephone: "",
            secteur_activite: "",
            disponibilite: "",
            password: "",
            status: "",
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
        if(nextProps.errors) {
            this.setState({errors: nextProps.errors});
        }
    }

    onChange(e) {
        this.setState({[e.target.name]:e.target.value});
    }
    
    onSubmit(e) {
        e.preventDefault();
        const newUser = {
            prenom: this.state.prenom,
            nom: this.state.nom,
            email: this.state.email,
            telephone: this.state.telephone,
            secteur_activite: this.state.secteur_activite,
            disponibilite: this.state.disponibilite,
            password: this.state.password,
            status: this.state.status
        }

        this.props.createNewUser(newUser, this.props.history);
    }

    render() {
        const { errors } = this.state;
        return(
            <div className="login">
                <div className="login-triangle"></div>
                
                <p className="login-header"><strong>S</strong>kilters</p>

                <form className="login-container" onSubmit={this.onSubmit}>
                    <p><input name="prenom" type="text" placeholder="Prenom" value={this.state.prenom} onChange={this.onChange} required /></p>
                    <p><input name="nom" type="text" placeholder="Nom" value={this.state.nom} onChange={this.onChange} required /></p>
                    <p><input name="email" type="email" placeholder="Email" value={this.state.email} onChange={this.onChange} className={classnames({"is-invalid": errors.email})} /></p>
                    <div className="error_display">{errors.email && (<div className="invalid-feedback">{errors.email}</div>)}</div>
                    <p><input name="telephone" type="number" placeholder="telephone" value={this.state.telephone} onChange={this.onChange} required /></p>
                    <p><input name="secteur_activite" type="text" placeholder="Secteur d'activite" value={this.state.secteur_activite} onChange={this.onChange} required /></p>
                    <p><input name="disponibilite" type="date" placeholder="DD-MM-YYYY" value={this.state.disponibilite} onChange={this.onChange} required /></p>
                    <p><input name="password" type="password" placeholder="Password" value={this.state.password} onChange={this.onChange} required /></p>
                    <p><input name="status" type="text" placeholder="valide / non_valide / actif" title="valide, non_valide ou actif" pattern="valide|non_valide|actif" value={this.state.status} onChange={this.onChange} required /></p>
                    <p><input type="submit" value="SIGN-UP"/></p>
                </form>
            </div>
        );
    }
}

Register.propTypes = {
    createNewUser: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired,
    security: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    errors: state.errors,
    security: state.security
});
export default connect(mapStateToProps, {createNewUser})(Register);
