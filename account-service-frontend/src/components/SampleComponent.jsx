import React, {useEffect, useState} from 'react'
import {customerDetails} from '../services/CustomerService'
import { useNavigate, useParams } from 'react-router-dom'

const CustomerInfoComponent = () => {
	const navigator = useNavigate();
	const {customerId} = useParams();
	const dummy = [{
		'id':1,
		'transaction':[]
	}]
	
	const[customerIds, setCustomerIds] = useState('')
	const[customerName, setCustomerName] = useState('')
	const[customerSurname, setCustomerSurname] = useState('')
	const[customerAccDetails, setCustomerAccDetails] = useState([])
	const[customerTransaction, setCustomerTransaction] = useState([])
	const[customerTransactionId, setCustomerTransactionId] = useState('')
	
	useEffect(() => {
		//console.log(customerId);
		//console.log(customerDetails);
		customerDetails(customerId).then((response) => 
		{
			console.log(response.data);
			console.log(response.data.accounts);
			console.log(response.data.accounts.transactions);
			setCustomerTransaction(response.data);
			setCustomerIds(response.data.id);
			setCustomerName(response.data.name);
			setCustomerSurname(response.data.surname);
			setCustomerAccDetails(response.data.accounts);
			setCustomerTransactionId(response.data.accounts.transactions.id);
		}).catch(error => 
		console.error(error));
		//navigator('/bankHomePage')
	}, [customerId])
	


function addAccount(){
	navigator('/v1/account')
}
render() {
return (
	<div className='container'>
		<h2 className='text-center'>Customer Info</h2>
			<table className='table table-striped table-bordered'>
				<thead>
					<tr>
						<th>customerid</th>
						<th>name</th>
						<th>surname</th>
						<th>transactionID</th>
						
						</tr>
						</thead>
						<tbody>					
							
							<tr key = {customerTransaction.id}>
							<td>{customerTransaction.id}</td>
							<td>{customerName}</td>
							<td>{customerSurname}</td>
							
							
							</tr>
							{
							customerTransaction.accounts.transactions.map(dummydat =>
							<td>{dummydat.id}</td>
							)
													
						}
						
					</tbody>	
				</table>		
			</div>
						
	
)
}
}
export default CustomerInfoComponent
