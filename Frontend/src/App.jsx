import {Route, Routes, Navigate} from 'react-router-dom'

import { useState, useEffect } from 'react';
import Registro from './pages/Register/Register'
import Login from './pages/login/Login'
import Recuperarpassword from './pages/RecuperarPassword/RecuperarPassword'
import IngresarToken from './pages/IngresoToken/ingresoToken'

import LandingPage  from './pages/landingPage/landingPage'
import './styles.css'

import IngresoNuevacontrasenia from './pages/IngresoNuevaContrasenia/IngresoNuevacontrasenia'


function App (){
	
	
	
	  const [isAuth, setIsAuth] = useState(() => {
	    const token = localStorage.getItem('token');
		console.log(token)
	    // Si hay token devuelve true, si no, devuelve false
	    return token ? true : false; 
	  });

	return(
		
		<Routes>
		<Route path = "/" element = {<LandingPage/>}/>
		<Route path = "/ingresoToken" element = {<IngresarToken/>}/>
		<Route path="/login" element={isAuth ? <Navigate to="/" /> : <Login setAuth={setIsAuth} />}/>
		<Route path = "/registro" element = {<Registro/>} />
		<Route path = "/recuperarpassword" element = {<Recuperarpassword/>} />
		<Route path = "/ingresarnuevapassword" element = {<IngresoNuevacontrasenia/>} />
		
		</Routes>
	);
	
	
	
}

export default App; 