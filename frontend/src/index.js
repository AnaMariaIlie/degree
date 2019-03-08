import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import App from './App';
import './App.css';
import Edit from './components/Edit';
import Create from './components/Create';
import Show from './components/Show';
import ShowIngredient from './components/ShowIngredient';
import EditIngredient from './components/EditIngredient';
import ShowInteraction from './components/ShowInteraction';
import EditInteraction from './components/EditInteraction';
import ShowListIngredients from './components/ShowListIngredients';

ReactDOM.render(
    <Router>
        <div>
            <Route exact path='/' component={App} />
            <Route path='/edit/:id' component={Edit} />
            <Route path='/create' component={Create} />
            <Route path='/show/:id' component={Show} />
            <Route path='/showIngredient/:id' component={ShowIngredient} />
            <Route path='/editIngredient/:id' component={EditIngredient} />
            <Route path='/showInteraction/:id' component={ShowInteraction} />
            <Route path='/editInteraction/:id' component={EditInteraction} />
            <Route path='/showListIngredients' component={ShowListIngredients} />
            
        </div>
    </Router>,
    document.getElementById('root')
);