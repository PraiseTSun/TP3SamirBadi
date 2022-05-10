import React, {Component} from "react";

class Administrator extends Component{
    state = {
        loans: [],
        debts: []
    }

    async getLoans(event, index){
        const loan = await(await fetch(`/react/loans/${index}`)).json();
        this.setState({loans: loan});
    }

    async getDebts(event, index){
        const response = await fetch(`/react/debts/${index}`);
        const body = await response.json();
        this.setState({debts: body});
    }

    async createAdmin(event){
        let firstName = document.getElementById("fname").value;
        let lastName = document.getElementById("lname").value;
        let password = document.getElementById("password").value;
        await fetch(`/react/create_admin_${firstName}_${lastName}_${password}`);
    }

    render(){
        const loans = this.state.loans;
        const debts = this.state.debts;
        return(
            <div>
                <h1>Administrator</h1>
                <h2>Create Administrator</h2>
                <form onSubmit={
                    (e) =>{this.createAdmin(e);}
                }>
                    <label>First Name:  </label> <input id="fname" type="text"/><br></br>
                    <label>Last Name:   </label> <input id="lname" type="text"/><br></br>
                    <label>Password:    </label> <input id="password" type="text"/><br></br>
                    <input type="submit" value="Create" />
                </form>

                <label>Month: 
                    <form onChange={
                        (e) =>{
                            let month = document.getElementById("month").value;
                            this.getLoans(e, month);
                            this.getDebts(e, month);
                        }
                    }>
                        <input id="month" type="number" min="1" max="12"/>
                    </form> 
                </label>
                

                <h2>Loans</h2>
                <table>
                    <thead>
                        <tr>
                            <th>id</th>
                            <th>Name</th>
                            <th>Loan</th>
                            <th>Return</th>
                        </tr>
                    </thead>
                    <tbody>
                        {loans.map( (loan) =>
                            <tr key={loan.id}>
                                <td>{loan.id}</td>
                                <td>{loan.documentName}</td>
                                <td>{loan.dateOfLoan}</td>
                                <td>{loan.dateOfReturn}</td>
                            </tr>
                        )}
                    </tbody>
                </table>
                <h2>Debts</h2>
                <table>
                    <thead>
                        <tr>
                            <th>id</th>
                            <th>Date</th>
                            <th>Fine</th>
                        </tr>
                    </thead>
                    <tbody>
                        {debts.map( (debt) =>
                            <tr key={debt.id}>
                                <td>{debt.id}</td>
                                <td>{debt.dateOfDebt}</td>
                                <td>{debt.fine}</td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        );
    }
}

export default Administrator;