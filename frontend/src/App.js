import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { global_url } from './env.js'

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            drugs: []
        };
    }

    componentDidMount() {
        axios.get(global_url + '/drugs')
            .then(res => {
                this.setState({ drugs: res.data });
                console.log(this.state.drugs);
            });
    }

    render() {
        return (
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            DRUGS LIST
                        </h3>
                    </div>
                    &emsp;
                    <div class="panel-body">
                        <h4><Link to="/create"  className="h-color" ><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>Add Drug</Link></h4>
                        <table class="table table-stripe">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Ingredients</th>
                            </tr>
                            </thead>
                            <tbody>
                            {this.state.drugs.map(c =>
                                <tr>
                                    <td><Link to={`/show/${c.id}`}><b>{c.name}</b></Link></td>
                                    <td>{c.ingredients.map(i => <small><Link to={`/showIngredient/${i.id}`}>{i.name + ".  "}</Link></small>)}</td>
                                </tr>
                            )}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        );
    }
}

export default App;