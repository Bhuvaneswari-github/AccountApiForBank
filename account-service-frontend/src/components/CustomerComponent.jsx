import React, { useState } from 'react'
import { accountcreation } from '../services/CustomerService'
import { useNavigate } from 'react-router-dom'

const CustomerComponent = () => {
	const[ customerId, setCustomerId ] = useState('')
	const[ initialCredit, setInitialCredit] = useState('')
	const navigator = useNavigate();

	
	function handleCustomerId(e){
		setCustomerId(e.target.value);
	}
	function handleInitialCredit(e){
		setInitialCredit(e.target.value);
	}
	function createAccount(e){
		e.preventDefault();
		
		const accountDetails = {customerId,initialCredit}
		console.log(accountDetails)
		accountcreation(accountDetails).then((response) => {console.log(response.data);
			navigator('/bankHomepage')})
	
	}
	
	
	return (
		<div className='container'>
			<div className='row'>
				<div className='card'>
					<h2 className='text-center'>Account creation</h2>
						<div className='card-body'>
						<form>
							<div className='form-group mb-2'>
								<label className='form-label'>ID</label>
								<input
									type='text'
									placeholder='Enter Customer ID'
									name='ID'
									value={customerId}
									className='form-control'
									onChange={handleCustomerId}
									>
								</input>
								
							</div>
							
							<div className='form-group mb-2'>
								<label className='form-label'>InitialCredit</label>
								<input
									type='text'
									placeholder='Enter Customer ID'
									name='initialCredit'
									value={initialCredit}
									className='form-control'
									onChange={handleInitialCredit}
									>
								</input>
								
							</div>
							<button className='btn btn-primary mb-2' onClick={createAccount}>Submit</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	)
	
}

export default CustomerComponent