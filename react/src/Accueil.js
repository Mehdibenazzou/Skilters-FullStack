import React, {Component} from 'react';
import './Accueil.css';
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { logout } from "./Actions/securityActions";

class Accueil extends Component {
    logout() {
        this.props.logout();
        window.location.href = "/";
    }

    render(){
        const { validToken, user } = this.props.security;
        return (
            <div>
            <header className="main-header">
                <a href="/" className="logo">
                    <span className="logo-mini"><b>S</b>KS</span>
                    <span className="logo-lg"><b>Skilters</b>APP</span>
                </a>
                <nav className="navbar navbar-static-top">
                    <a className="sidebar-toggle" data-toggle="push-menu" role="button">
                        <span className="sr-only">Toggle navigation</span>
                    </a>
                    <div className="navbar-custom-menu">
                        <ul className="nav navbar-nav">
                        </ul>
                    </div>
                </nav>
            </header>
            <aside className="main-sidebar">
                <section className="sidebar">
                    <div className="user-panel">
                        <div className="pull-left image">
                            <img src="img/avatar5.png" className="img-circle" alt="User Image" />
                        </div>
                        <div className="pull-left info">
                            <p>{user.prenom+" "+user.nom}</p>
                            <a href="#"><i className="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <form action="#" method="get" className="sidebar-form">
                        <div className="input-group">
                        <input type="text" name="q" className="form-control" placeholder="Search..." />
                        <span className="input-group-btn">
                                <button type="submit" name="search" id="search-btn" className="btn btn-flat"><i className="fa fa-search"></i>
                                </button>
                            </span>
                        </div>
                    </form>
                    <ul className="sidebar-menu" data-widget="tree">
                        <li className="header">MAIN NAVIGATION</li>
                        <li>
                        <a href="/accueil">
                            <i className="fa fa-home"></i> <span>Accueil</span>
                            <span className="pull-right-container">
                            </span>
                        </a>
                        </li>
                        <li>
                        <a href="/users">
                            <i className="fa fa-table"></i> <span>Liste d'utilisateur</span>
                            <span className="pull-right-container">
                            </span>
                        </a>
                        </li>
                        <li>
                        <a onClick={this.logout.bind(this)}>
                            <i className="fa fa-share"></i> <span>Se déconnecter</span>
                            <span className="pull-right-container">
                            </span>
                        </a>
                        </li>
                    </ul>
                </section>
            </aside>
            <div className="content-wrapper">
                <section className="content">
                    <div className="row">
                        <div className="center-screen">
                        <img src="img/skilters.jpeg" className="col-md-12 col-xs-6"/>
                        </div>
                    </div>
                </section>
            </div>
            <footer class="main-footer">
                <div class="pull-right hidden-xs">
                <b>Version</b> 1.0.0
                </div>
                Copyright © 2018 El Mehdi Benazzou for Skilters.
            </footer>
            </div>
        )
    }
}

Accueil.propTypes = {
    logout: PropTypes.func.isRequired,
    security: PropTypes.object.isRequired
  };
  
  const mapStateToProps = state => ({
    security: state.security
  });
  
  export default connect(
    mapStateToProps,
    { logout }
  )(Accueil);