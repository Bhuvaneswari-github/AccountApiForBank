import React from 'react'
import { useNavigate } from 'react-router-dom'

const HomePageComponent = () => {
	const navigator = useNavigate();


function createAccount(){
	navigator('/v1/account')
}
function userInfo(){
	navigator('/v1/customer')
}

	return (
		<div>HomePageComponent
		<button className='btn btn-primary mb-2' onClick={userInfo}>UserInfo</button>
		<button className='btn btn-primary mb-2' onClick={createAccount}>CreateAccount</button>
		</div>
	)	
}
export default HomePageComponent