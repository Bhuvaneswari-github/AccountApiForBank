import './App.css'
import CustomerComponent from './components/CustomerComponent'
import AddCustomerAccountComponent from './components/AddCustomerAccountComponent'
import HomePageComponent from './components/HomePageComponent'
import CustomerInfoComponent from './components/CustomerInfoComponent'
import {BrowserRouter, Routes, Route} from 'react-router-dom'

function App() {
 

  return (
    <>
		<BrowserRouter>
			<Routes>
		<Route path='/bankHomepage' element= { <HomePageComponent />}></Route>
		<Route path='/v1/customer' element= { <AddCustomerAccountComponent />}></Route>
		<Route path='/info/:customerId' element= { <CustomerInfoComponent />}></Route>
		<Route path='/v1/account' element= { <CustomerComponent />}></Route>
		</Routes>
		
		</BrowserRouter>
		</>
  )
}

export default App
