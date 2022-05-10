import React, {Component} from "react";

class Employe extends Component{
    state = {
        documents: [],
        input:{
            firstName: 'Samir',
            lastName: 'Badi',
            password: 'PraiseTSun'
        },
        client: null,
        clients: []
    }

    async componentDidMount(){
        this.getDocuments();
        this.getClients();
    }

    async getDocuments(){
        const document = await( await fetch('/react/documents')).json();
        this.setState({documents: document});
    }

    async getClients(){
        const clients = await( await fetch('/react/clients')).json();
        this.setState({clients: clients});
    }

    async selectClient(event, id){
        event.preventDefault();
        const client = await ( await fetch(`/react/client_${id}`)).json();
        this.setState({client: client});
    }
    
    async createBook(event){
        let title   = document.getElementById("title").value;
        let author  = document.getElementById("author").value;
        let editor  = document.getElementById("editor").value;
        let year    = document.getElementById("year").value;
        let genre   = document.getElementById("genre").value;
        let pages   = document.getElementById("pages").value;

        event.preventDefault();
        await fetch(`/react/create_book_${title}_${author}_${editor}_${genre}_${year}_${pages}`);
    }

    async createCD(event){
        let title   = document.getElementById("title").value;
        let author  = document.getElementById("author").value;
        let editor  = document.getElementById("editor").value;
        let year    = document.getElementById("year").value;
        let genre   = document.getElementById("genre").value;

        event.preventDefault();
        await fetch(`/react/create_cd_${title}_${author}_${editor}_${genre}_${year}`);
    }

    async createDVD(event){
        let title   = document.getElementById("title").value;
        let author  = document.getElementById("author").value;
        let editor  = document.getElementById("editor").value;
        let year    = document.getElementById("year").value;
        let genre   = document.getElementById("genre").value;

        event.preventDefault();
        await fetch(`/react/create_dvd_${title}_${author}_${editor}_${genre}_${year}`);
    }

    async returnDocument(event, index){
        await( await fetch(`/react/return/${this.state.client.id}_${index}`)).json();
    }

    async payDebt(event, debt){
        await( await fetch(`/react/pay/${this.state.client.id}_${debt}`)).json();
    }


    async byNameDocument(event){
        let value = document.getElementById("nameDoc").value;
        event.preventDefault();
        const body = await (await fetch(`/react/documents_title_${value}`)).json();
        this.setState({documents: body});
    }

    async createLoan(event, id){
        await fetch(`/react/loan/${this.state.client.id}_${id}`);
    }

    async createEmploye(event){
        let firstName = document.getElementById("fname").value;
        let lastName = document.getElementById("lname").value;
        let password = document.getElementById("password").value;
        await fetch(`/react/create_employe_${firstName}_${lastName}_${password}`);
    }

    clientSection(){
        if(this.state.client == null)
            return(
                <div>
                <h2>Clients</h2>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Resident</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.clients.map( (client) =>
                            <tr key={client.id}>
                                <td>{client.id}</td>
                                <td>{client.firstName}</td>
                                <td>{client.lastName}</td>
                                <td>{client.resident}</td>
                                <td>
                                    <form onSubmit={
                                        (e) =>{this.selectClient(e, client.id);}
                                    }>
                                        <input type="submit" value="Select"/>
                                    </form>                                
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
                </div>
            );
        return (
            <div>
                <h1>Client</h1>
                <h2>Loans</h2>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Loan</th>
                            <th>Return</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.client.loans.map( (loan) =>
                            <tr key={loan.id}>
                                <td>{loan.id}</td>
                                <td>{loan.documentName}</td>
                                <td>{loan.dateOfLoan}</td>
                                <td>{loan.dateOfReturn}</td>
                                <td>
                                    <form onSubmit={
                                        (e) =>{this.returnDocument(e, loan.id);}
                                    }>
                                        <input type="submit" value="Return"/>
                                    </form>                                
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
                <h2>Depts</h2>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Date</th>
                            <th>Fine</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.client.debts.map( (debt) =>
                            <tr key={debt.id}>
                                <td>{debt.id}</td>
                                <td>{debt.dateOfDebt}</td>
                                <td>{debt.fine}</td>
                                <td>
                                    <form onSubmit={
                                        (e) =>{this.payDebt(e, debt.id);}
                                    }>
                                        <input type="submit" value="Pay"/>
                                    </form>                                
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
                <h2>Loan a document</h2>
                <form onChange={
                    (e) => {this.byNameDocument(e);}
                }>
                    <input id="nameDoc" type="text"/>
                </form>
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Author</th>
                            <th>Editor</th>
                            <th>Genre</th>
                            <th>Year</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.documents.map( (document) =>
                            <tr key={document.id}>
                                <td>{document.title}</td>
                                <td>{document.author}</td>
                                <td>{document.editor}</td>
                                <td>{document.genre}</td>
                                <td>{document.publicationYear}</td>
                                <td>
                                    <form onSubmit={
                                        (e) =>{this.createLoan(e, document.id);}
                                    }>
                                        <input type="submit" value="Loan"/>
                                    </form>                                
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        );
    }

    render(){
        return(
            <div>
                <h1>Employe</h1>
                <h2>Create Employe</h2>
                <form onSubmit={
                    (e) =>{this.createEmploye(e);}
                }>
                    <label>First Name:  </label> <input id="fname" type="text"/><br></br>
                    <label>Last Name:   </label> <input id="lname" type="text"/><br></br>
                    <label>Password:    </label> <input id="password" type="text"/><br></br>
                    <input type="submit" value="Create" />
                </form>
                <h2>Create Document</h2>
                <div>
                    <label>Title    </label> <input id="title" type="text"/><br></br>
                    <label>Author   </label> <input id="author" type="text"/><br></br>
                    <label>Editor   </label> <input id="editor" type="text"/><br></br>
                    <label>Year     </label> <input id="year" type="number" max={2022}/><br></br>
                    <label>Genre    </label> <input id="genre" type="text"/><br></br>
                    <label>Pages    </label> <input id="pages" type="number" min={0}/><br></br>
                    <form onSubmit={
                        (e) => {this.createBook(e)}
                    }>
                        <input type="submit" value="Create Book"/>
                    </form>
                    <form onSubmit={
                        (e) => {this.createCD(e)}
                    }>
                        <input type="submit" value="Create CD"/>
                    </form>
                    <form onSubmit={
                        (e) => {this.createDVD(e)}
                    }>
                        <input type="submit" value="Create DVD"/>
                    </form>
                </div>
                {this.clientSection()}
            </div>
        );
    }
}
export default Employe;