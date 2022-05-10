import React, {Component} from "react";

class Client extends Component {
    state = {
        documents: []
    };

    async componentDidMount(){
        this.allDocument();
    }

    async allDocument(){
        const response = await fetch('/react/documents');
        const body = await response.json();
        this.setState({documents: body});
    }

    async byNameDocument(value){
        const response = await fetch(`/react/documents_title_${value}`);
        const body = await response.json();
        this.setState({documents: body});
    }

    async byAuthorDocument(value){
        const response = await fetch(`/react/documents_author_${value}`);
        const body = await response.json();
        this.setState({documents: body});
    }

    async byEditorDocument(value){
        const response = await fetch(`/react/documents_editor_${value}`);
        const body = await response.json();
        this.setState({documents: body});
    }

    async byGenreDocument(value){
        const response = await fetch(`/react/documents_genre_${value}`);
        const body = await response.json();
        this.setState({documents: body});
    }

    async byYearDocument(value){
        const response = await fetch(`/react/documents_year_${value}`);
        const body = await response.json();
        this.setState({documents: body});
    }

    changeHandler(event){
        let value = document.getElementById("research").value;
        let categorie = document.getElementById("categorie").value;

        switch(categorie){
            case "title":
                this.byNameDocument(value);
                break;
            case "author":
                this.byAuthorDocument(value);
                break;
            case "editor":
                this.byEditorDocument(value);
                break;
            case "genre":
                this.byGenreDocument(value);
                break;
            case "year":
                this.byYearDocument(value);
                break;
            default:
                this.allDocument();
                break;
        }
    }

    async createClient(event){
        let firstName = document.getElementById("fname").value;
        let lastName = document.getElementById("lname").value;
        let password = document.getElementById("password").value;
        let resident = document.getElementById("resident").value;
        await fetch(`/react/create_client_${firstName}_${lastName}_${password}_${resident}`);
    }

    render(){
        const {documents} = this.state;
        return(
            <div>
                <h1>Client</h1>
                <h2>Create Account</h2>
                <form onSubmit={
                    (e) =>{this.createClient(e);}
                }>
                    <label>First Name:  </label> <input id="fname" type="text"/><br></br>
                    <label>Last Name:   </label> <input id="lname" type="text"/><br></br>
                    <label>Password:    </label> <input id="password" type="text"/><br></br>
                    <label>Resident:    </label> <input id="resident" type="text"/><br></br>
                    <input type="submit" value="Create" />
                </form>
                <h2>Documents</h2>
                <form onChange={
                    (e) => {this.changeHandler(e);}
                }>
                    <select id="categorie">
                        <option value="all">All</option>
                        <option value="title">Title</option>
                        <option value="author">Author</option>
                        <option value="editor">Editor</option>
                        <option value="genre">Genre</option>

                    </select>
                    <input id="research" type="text"/>
                </form>
                
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Author</th>
                            <th>Editor</th>
                            <th>Genre</th>
                            <th>Year</th>
                        </tr>
                    </thead>
                    <tbody>
                        {documents.map( (document) =>
                            <tr key={document.id}>
                                <td>{document.title}</td>
                                <td>{document.author}</td>
                                <td>{document.editor}</td>
                                <td>{document.genre}</td>
                                <td>{document.publicationYear}</td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        );
    }
}

export default Client;