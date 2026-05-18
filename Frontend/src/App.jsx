import {Route, Routes} from 'react-router-dom'


import Registro from './pages/Register/Register'
import Login from './pages/login/Login'
import Recuperarpassword from './pages/RecuperarPassword/RecuperarPassword'
import IngresarToken from './pages/IngresoToken/ingresoToken'
import LandingPage from './pages/landingPage/landingPage'
import './styles.css'

function App (){
	
	return(
		
		<Routes>
		<Route path = "/" element = {<LandingPage/>}/>
		<Route path = "/ingresoToken" element = {<IngresarToken/>}/>
		<Route path="/login" element = {<Login/>}/>
		<Route path = "/registro" element = {<Registro/>} />
		<Route path = "/recuperarpassword" element = {<Recuperarpassword/>} />
		
		</Routes>
	);
	
	
	
}

export default App; 