import {Component} from "react";
import React from "react";

class CustomerInfoComponent extends Component {
	state = {
        customer: {
            accounts: []
        }
    };


    async componentDidMount() {
	const studentId = window.location.href.split('/')[4]
	
        const response = await fetch(`http://localhost:8080/v1/customer/${studentId}`);
	
        const body = await response.json();

        this.setState({customer: body});
    }


    render() {
        const {customer} = this.state;
        return (
		     <div className="App">
                <header className="App-header">
                    <div className="App-intro">
                        <h2>Customer Info</h2>
                        <div key={customer.id}>
                             Customer Id : {customer.id} <br/>
							 Customer First Name : {customer.name} <br/>
							 Customer Surname : {customer.surname} <br/>
                            {
                                customer.accounts.map(account =>
                                    (<p> Account Id : {account.id} <br/> 
										 Account balance : {account.balance} <br/>
										 Account creation date : {account.creationDate}
                                        {
                                            account.transactions.map(transaction =>
                                                (<p> Transaction Id : {transaction.id} <br/>
													 Transaction Amount : {transaction.amount} <br/>
                                                     Transaction Date : {transaction.transactionDate} <br/>
													 Transaction Type : {transaction.transactionType}
                                                </p>)
                                            )
                                        }
                                    </p>)
                                )
                            }
                        </div>
                    </div>

                </header>
            </div>
        );
    }
}

export default CustomerInfoComponent;
