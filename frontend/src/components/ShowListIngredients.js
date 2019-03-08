import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { global_url } from '../env.js';

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            ingredients: [],
            activePage: 1
        };

        this.handlePageChange = this.handlePageChange.bind(this);

    }

    handlePageChange(pageNumber) {
        console.log(`active page is ${pageNumber}`);
        this.setState({activePage: pageNumber});
    }

    componentDidMount() {
        axios.get(global_url + '/ingredients')
            .then(res => {
                this.setState({ ingredients: res.data });
            });
    }

    render() {

        return (
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            INGREDIENTS LIST
                        </h3>
                    </div>
                    &emsp;
                    <div class="panel-body">
                        <h4><Link to="/create"  className="h-color" ><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>Add Ingredient</Link></h4>

                      <table class="table table-stripe">
                            <thead>
                            <tr>
                                <th>Name</th>
                            </tr>
                            </thead>

                            <tbody>
                            {this.state.ingredients.map(c =>
                                <tr>
                                    <td><Link to={`/showIngredient/${c.id}`}><b>{c.name}</b></Link></td>
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