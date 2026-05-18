import {Route, Routes} from 'react-router-dom'


import Registro from './pages/Register/Register'
import Login from './pages/login/Login'
import Recuperarpassword from './pages/RecuperarPassword/RecuperarPassword'
import IngresarToken from './pages/IngresoToken/ingresoToken'
import IngresoNuevacontrasenia from './pages/IngresoNuevaContrasenia/IngresoNuevacontrasenia'

function App (){
	
	return(
		
		<Routes>
		<Route path = "/ingresoToken" element = {<IngresarToken/>}/>
		<Route path="/" element = {<Login/>}/>
		<Route path = "/registro" element = {<Registro/>} />
		<Route path = "/recuperarpassword" element = {<Recuperarpassword/>} />
		<Route path = "/ingresarnuevapassword" element = {<IngresoNuevacontrasenia/>} />
		
		</Routes>
	);
	
	
	
}

export default App; 