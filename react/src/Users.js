import React, {Component} from 'react';
import Datatable from './Datatable';
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { logout } from "./Actions/securityActions";

class Users extends Component {
    state = {
        isLoading: true,
        users: []
    };

    logout() {
        this.props.logout();
        window.location.href = "/";
    }

    async componentDidMount() {
        const response = await fetch('/users/listusers');
        const body = await response.json();
        this.setState({ users: body, isLoading: false });
    }

    componentWillUnmount() {
        this.$el.Datatable.destroy(true);
    }

    render(){
        const {users, isLoading} = this.state;
        const { validToken, user } = this.props.security;
        if (isLoading) {
            return <p>Loading...</p>;
        }
        return (
            <div>
            <header className="main-header">
                <a href="/" className="logo">
                    <span className="logo-mini"><b>S</b>KS</span>
                    <span className="logo-lg"><b>Skilters</b>APP</span>
                </a>
                <nav className="navbar navbar-static-top">
                    <a href="#" className="sidebar-toggle" data-toggle="push-menu" role="button">
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
            <section class="content-header">
                <h1>
                    Listes des utilisateurs<br/>
                    <small>Vous pouvez filtrer les utilisateurs</small>
                </h1>
            </section>
            <section className="content">
                    <div className="row">
                        <div className="col-md-12">
                            <div className="box">
                                <div className="box-header with-border">
                                    <h3 className="box-title">Utilisateurs inscrits</h3>
                                </div>
                                <div className="box-body">
                                        <div className="table table-bordered col-md-12">
                                            <Datatable data={users}></Datatable>
                                        </div>
                                </div>
                            </div>
                        </div>
                    </div>
            </section>
            </div>
            </div>
        )
    }
}

Users.propTypes = {
    logout: PropTypes.func.isRequired,
    security: PropTypes.object.isRequired
  };
  
  const mapStateToProps = state => ({
    security: state.security
  });
  
  export default connect(
    mapStateToProps,
    { logout }
)(Users);
