(window.webpackJsonp=window.webpackJsonp||[]).push([[0],{23:function(e,t,n){e.exports=n(55)},52:function(e,t,n){},55:function(e,t,n){"use strict";n.r(t);var a=n(0),l=n.n(a),i=n(13),r=n.n(i),c=n(58),s=n(57),o=(n(29),n(7)),m=n(8),d=n(10),u=n(9),h=n(11),p=n(56),E=n(5),g=n.n(E),b=function(e){function t(e){var n;return Object(o.a)(this,t),(n=Object(d.a)(this,Object(u.a)(t).call(this,e))).state={ingredients:[]},n}return Object(h.a)(t,e),Object(m.a)(t,[{key:"componentDidMount",value:function(){var e=this;g.a.get("/ingredients").then(function(t){e.setState({ingredients:t.data}),console.log(e.state.ingredients)})}},{key:"render",value:function(){return l.a.createElement("div",{class:"container"},l.a.createElement("div",{class:"panel panel-default"},l.a.createElement("div",{class:"panel-heading"},l.a.createElement("h3",{class:"panel-title"},"INGREDIENTS LIST")),l.a.createElement("div",{class:"panel-body"},l.a.createElement("h4",null,l.a.createElement(p.a,{to:"/create"},l.a.createElement("span",{class:"glyphicon glyphicon-plus-sign","aria-hidden":"true"})," Add Ingredient")),l.a.createElement("table",{class:"table table-stripe"},l.a.createElement("thead",null,l.a.createElement("tr",null,l.a.createElement("th",null,"Name"),l.a.createElement("th",null,"Interactions"))),l.a.createElement("tbody",null,this.state.ingredients.map(function(e){return l.a.createElement("tr",null,l.a.createElement("td",null,l.a.createElement(p.a,{to:"/show/".concat(e.id)},e.name)),l.a.createElement("td",null,e.interactions))}))))))}}]),t}(a.Component),f=(n(52),function(e){function t(e){var n;return Object(o.a)(this,t),(n=Object(d.a)(this,Object(u.a)(t).call(this,e))).onChange=function(e){var t=n.state.ingredient;t[e.target.name]=e.target.value,n.setState({ingredient:t})},n.onSubmit=function(e){e.preventDefault();var t=n.state.ingredient,a=t.name,l=t.interactions;g.a.put("/ingredient/"+n.props.match.params.id,{name:a,interactions:l}).then(function(e){n.props.history.push("/show/"+n.props.match.params.id)})},n.state={ingredient:{}},n}return Object(h.a)(t,e),Object(m.a)(t,[{key:"componentDidMount",value:function(){var e=this;g.a.get("/ingredient/"+this.props.match.params.id).then(function(t){e.setState({ingredient:t.data}),console.log(e.state.ingredient)})}},{key:"render",value:function(){return l.a.createElement("div",{class:"container"},l.a.createElement("div",{class:"panel panel-default"},l.a.createElement("div",{class:"panel-heading"},l.a.createElement("h3",{class:"panel-title"},"EDIT Ingredient")),l.a.createElement("div",{class:"panel-body"},l.a.createElement("h4",null,l.a.createElement(p.a,{to:"/show/".concat(this.state.ingredient.id)},l.a.createElement("span",{class:"glyphicon glyphicon-eye-open","aria-hidden":"true"})," Ingredient List")),l.a.createElement("form",{onSubmit:this.onSubmit},l.a.createElement("div",{class:"form-group"},l.a.createElement("label",{for:"name"},"Name:"),l.a.createElement("input",{type:"text",class:"form-control",name:"name",value:this.state.ingredient.name,onChange:this.onChange,placeholder:"Name"})),l.a.createElement("div",{class:"form-group"},l.a.createElement("label",{for:"title"},"Interactions:"),l.a.createElement("input",{type:"text",class:"form-control",name:"interactions",value:this.state.ingredient.interactions,onChange:this.onChange,placeholder:"Interactions"})),l.a.createElement("button",{type:"submit",class:"btn btn-default"},"Update")))))}}]),t}(a.Component)),v=function(e){function t(){var e;return Object(o.a)(this,t),(e=Object(d.a)(this,Object(u.a)(t).call(this))).onChange=function(t){var n=e.state;n[t.target.name]=t.target.value,e.setState(n)},e.onSubmit=function(t){t.preventDefault();var n=e.state,a=n.name,l=n.interactions;g.a.post("/ingredient",{name:a,interactions:l}).then(function(t){e.props.history.push("/")})},e.state={name:"",interactions:[]},e}return Object(h.a)(t,e),Object(m.a)(t,[{key:"render",value:function(){var e=this.state,t=e.name,n=e.interactions;return l.a.createElement("div",{class:"container"},l.a.createElement("div",{class:"panel panel-default"},l.a.createElement("div",{class:"panel-heading"},l.a.createElement("h3",{class:"panel-title"},"ADD Ingredient")),l.a.createElement("div",{class:"panel-body"},l.a.createElement("h4",null,l.a.createElement(p.a,{to:"/"},l.a.createElement("span",{class:"glyphicon glyphicon-th-list","aria-hidden":"true"})," Ingredients List")),l.a.createElement("form",{onSubmit:this.onSubmit},l.a.createElement("div",{class:"form-group"},l.a.createElement("label",{for:"isbn"},"Name:"),l.a.createElement("input",{type:"text",class:"form-control",name:"name",value:t,onChange:this.onChange,placeholder:"Name"})),l.a.createElement("div",{class:"form-group"},l.a.createElement("label",{for:"publisher"},"Interactions:"),l.a.createElement("input",{type:"interactions",class:"form-control",name:"interactions",value:n,onChange:this.onChange,placeholder:"Interactions"})),l.a.createElement("button",{type:"submit",class:"btn btn-default"},"Submit")))))}}]),t}(a.Component),y=function(e){function t(e){var n;return Object(o.a)(this,t),(n=Object(d.a)(this,Object(u.a)(t).call(this,e))).state={ingredient:{}},n}return Object(h.a)(t,e),Object(m.a)(t,[{key:"componentDidMount",value:function(){var e=this;g.a.get("http://localhost:8080/ingredient/"+this.props.match.params.id).then(function(t){e.setState({ingredient:t.data}),console.log(e.state.ingredient)})}},{key:"delete",value:function(e){var t=this;console.log(e),g.a.delete("/ingredient/"+e).then(function(e){t.props.history.push("/")})}},{key:"render",value:function(){return console.log("Here"+this.state.ingredient.name),l.a.createElement("div",{class:"container"},l.a.createElement("div",{class:"panel panel-default"},l.a.createElement("div",{class:"panel-heading"},l.a.createElement("h3",{class:"panel-title"},"Ingredient Details")),l.a.createElement("div",{class:"panel-body"},l.a.createElement("h4",null,l.a.createElement(p.a,{to:"/"},l.a.createElement("span",{class:"glyphicon glyphicon-th-list","aria-hidden":"true"})," Ingredients List")),l.a.createElement("dl",null,l.a.createElement("dt",null,"Name:"),l.a.createElement("dd",null,this.state.ingredient.name),l.a.createElement("dt",null,"Interactions:"),l.a.createElement("dd",null,this.state.ingredient.interactions)),l.a.createElement(p.a,{to:"/edit/".concat(this.state.ingredient.id),class:"btn btn-success"},"Edit"),"\xa0",l.a.createElement("button",{onClick:this.delete.bind(this,this.state.ingredient.id),class:"btn btn-danger"},"Delete"))))}}]),t}(a.Component);r.a.render(l.a.createElement(c.a,null,l.a.createElement("div",null,l.a.createElement(s.a,{exact:!0,path:"/",component:b}),l.a.createElement(s.a,{path:"/edit/:id",component:f}),l.a.createElement(s.a,{path:"/create",component:v}),l.a.createElement(s.a,{path:"/show/:id",component:y}))),document.getElementById("root"))}},[[23,2,1]]]);
//# sourceMappingURL=main.6e1643d2.chunk.js.map