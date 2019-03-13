import React, { Component } from 'react';

class AddItemForm extends Component {
    createIngredient (e) {
        e.preventDefault();

        var ingredient = this.refs.ingredientName.value;

        if(typeof ingredient === 'string' && ingredient.length > 0) {
            this.props.addIngredient(ingredient);
            this.refs.ingredientForm.reset();
        }
    }
    render() {
        return(
            <form className="form-inline" ref="ingredientForm" onSubmit={this.createIngredient.bind(this)}>
                <div className="form-group">
                    <label for="ingredientItem">
                        Ingredient Name
                        <input type="text" id="ingredientItem" placeholder="e.x.lemmon" ref="ingredientName" className="form-control" />
                    </label>
                </div>
                <button type="submit" className="btn btn-primary">Add Ingredient</button>
            </form>
        )
    }
}

export default AddItemForm;