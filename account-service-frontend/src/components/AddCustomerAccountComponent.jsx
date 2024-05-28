import React, {useEffect, useState} from 'react'
import {customerDetails} from '../services/CustomerService'
import { useNavigate } from 'react-router-dom'

const AddCustomerAccountComponent = () => {
		const[ customerId, setCustomerId ] = useState('')
	const navigator = useNavigate();

function handleCustomerId(e){
		setCustomerId(e.target.value);
	}

function customerInfo(customerId){
		//customerId.preventDefault();
		
		navigator(`/info/${customerId}`)
	
	}

return (
	<div className='container'>
			<div className='row'>
				<div className='card'>
					<h2 className='text-center'>Customer Info</h2>
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
							
							<button className='btn btn-primary mb-2' onClick={() => customerInfo(customerId)}>Submit</button>
						</form>
					</div>
				</div>
			</div>
		</div>
)
}
export default AddCustomerAccountComponent