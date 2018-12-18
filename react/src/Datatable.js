import React, {Component} from 'react';
import './Datatable.css'

const $ = require('jquery');
$.DataTable = require('datatables.net');

export default class Datatable extends Component {
    componentDidMount(){
        this.$el = $(this.el)
        this.$el.DataTable (
            {
                data: this.props.data,
                columns: [
                    { 
                        title: "#",
                        data: "id"
                    },
                    { 
                        title: "Nom",
                        data: "nom"
                    },
                    { 
                        title: "Prenom",
                        data: "prenom"
                    },
                    { 
                        title: "Email",
                        data: "email"
                    },
                    { 
                        title: "Telephone",
                        data: "telephone"
                    },
                    { 
                        title: "Secteur d'activite",
                        data: "secteur_activite"
                    },
                    { 
                        title: "Disponibilite",
                        data: "disponibilite"
                    },
                    { 
                        title: "Status",
                        data: "status"
                    },
                ]
            }
        )
    }

    render() {
        return(
            <div>
                <table className="display" width="100%" ref={el => this.el = el}></table>
            </div>
        );
    }
}