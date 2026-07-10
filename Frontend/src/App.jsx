import {Route, Routes, Navigate} from 'react-router-dom'


// eslint-disable-next-line no-unused-vars
import { useState, useEffect } from 'react';
import Registro from './pages/Register/Register'
import Login from './pages/login/Login'
import Recuperarpassword from './pages/RecuperarPassword/RecuperarPassword'
import IngresarToken from './pages/IngresoToken/ingresoToken'
import Navbar from './components/Navbar/Navbar';
import LandingPage  from './pages/landingPage/landingPage'
import Juegos from './pages/Juegos/Juegos'
import VerificarToken from './components/verificacionToken/verificacionToken'
import Modificarperfil from './pages/modificarperfil/modificarperfil'
import AdministrarCompanias from './pages/AdministrarCompanias/companias'

import CrearGrupo from './pages/crearGrupo/crearGrupo'


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
		<>
		<Navbar autenticado={isAuth} className="nav"/>
		
		<Routes>
		<Route path = "/" element = {<LandingPage/>}/>
		<Route path = "/ingresoToken" element = {<IngresarToken/>}/>
		<Route path="/login" element={isAuth ? <Navigate to="/" /> : <Login setAuth={setIsAuth} />}/>
		<Route path = "/registro" element = {<Registro/>} />
		<Route path = "/recuperarpassword" element = {<Recuperarpassword/>} />
		<Route path = "/ingresarnuevapassword" element = {<IngresoNuevacontrasenia/>} />
		<Route path = "/Juegos" element = {<Juegos/>} />
		<Route path = "/AdministrarCompanias" element = {<AdministrarCompanias/>} />

		<Route path = "/Modificarperfil" element = {<Modificarperfil/>} />

		<Route 
		          path="/CrearGrupo" 
		          element={
		            <VerificarToken>
		              <CrearGrupo />
		            </VerificarToken>
		          }/>

		
		</Routes>
		
		</>
	);
	
	
	
}

export default App; 