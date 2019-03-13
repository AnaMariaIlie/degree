import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { global_url } from '../env.js';
import ItemsList from './utils/ItemsList';
import AddItemForm from './utils/AddItemForm';

class Create extends Component {

    constructor() {
        super();
        this.state = {
            name: '',
            ingredients: []
        };
    }
    onChange = (e) => {
        const state = this.state
        state[e.target.name] = e.target.value;
        this.setState(state);
    }

    onSubmit = (e) => {
        e.preventDefault();

        const { name, ingredients} = this.state;

        axios.post(global_url + '/drug', { name, ingredients});
            //.then((result) => {
               // this.props.history.push("/")
           // });
    }

    addIngredient(ingredient) {

        this.state.ingredients.push(ingredient);
        this.setState({ ingredients : this.state.ingredients });
    }

    render() {
        const { name, ingredients } = this.state;
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
                        <form>
                            <div class="form-group">
                                <label for="isbn">Name:</label>
                                <input type="text" class="form-control" name="name" value={name} onChange={this.onChange} placeholder="Name" />
                            </div>
                            <div class="form-group">
                                <label for="publisher">Ingredients:</label>
                                <ItemsList ingredients={this.state.ingredients} />
                                <AddItemForm addIngredient={this.addIngredient.bind(this)} />
                            </div>
                            <button type="submit" class="btn btn-default"  onClick={this.onSubmit.bind(this)}>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        );
    }
}

export default Create;