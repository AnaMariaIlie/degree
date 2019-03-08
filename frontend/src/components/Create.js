import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { global_url } from '../env.js'

class Create extends Component {

    constructor() {
        super();
        this.state = {
            name: '',
            interactions: []
        };
    }
    onChange = (e) => {
        const state = this.state
        state[e.target.name] = e.target.value;
        this.setState(state);
    }

    onSubmit = (e) => {
        e.preventDefault();

        const { name, interactions} = this.state;

        axios.post(global_url + '/drug', { name, interactions})
            .then((result) => {
                this.props.history.push("/")
            });
    }

    render() {
        const { name, interactions } = this.state;
        return (
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            ADD Drug
                        </h3>
                    </div>
                    <div class="panel-body">
                        <h4><Link to="/"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> Drugs List</Link></h4>
                        <form onSubmit={this.onSubmit}>
                            <div class="form-group">
                                <label for="isbn">Name:</label>
                                <input type="text" class="form-control" name="name" value={name} onChange={this.onChange} placeholder="Name" />
                            </div>
                            <div class="form-group">
                                <label for="publisher">Interactions:</label>
                                <input type="interactions" class="form-control" name="interactions" value={interactions} onChange={this.onChange} placeholder="Interactions" />
                            </div>
                            <button type="submit" class="btn btn-default">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        );
    }
}

export default Create;