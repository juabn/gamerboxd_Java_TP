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
import CreacionAdmin from './pages/creacionAdministrador/crearAdministrador'
import CrearJuegos from './pages/CrearJuegos/CrearJuegos'
import MenuPropuestas from './pages/Propuestas/MenuPropuestas'

import JuegoResenias from './pages/Juego/Juego'

import AdministrarCompanias from './pages/AdministrarCompanias/companias'
import Modificarcompanias from './pages/AdministrarCompanias/modificarcompanias'


import CrearGrupo from './pages/crearGrupo/crearGrupo'


import Propuestas from './pages/Propuestas/Propuestas'

import ModificarJuego from './pages/ModificarJuego/ModificarJuego'

import PaginaGrupo from './pages/AdministrarGrupo/PaginaGrupo'


import './styles.css'

import IngresoNuevacontrasenia from './pages/IngresoNuevaContrasenia/IngresoNuevacontrasenia'


import MenuGrupo from './pages/MenuGrupo/MenuGrupo'

import ListadoGrupos from './pages/AdministrarGrupo/ListadoGrupos'

import ModificarGrupo from './pages/AdministrarGrupo/ModificarGrupo'

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

		<Route path = "/juego/:id" element = {<JuegoResenias/>}/>

		<Route path = "/AdministrarCompanias" element = {<AdministrarCompanias/>} />
		<Route path = "/Modificarcompanias" element = {<Modificarcompanias/>} />


		<Route path = "/Modificarperfil" element = {<Modificarperfil/>} />
		
		<Route path = "/CreacionAdmin" element = {<CreacionAdmin/>} />
		
		<Route path = "/CrearJuegos" element = {<CrearJuegos/>} />
		
		<Route path = "/Propuestas" element = {<Propuestas/>} />
		<Route path = "/MenuPropuestas" element = {<MenuPropuestas/>} />
		<Route path = "/ModificarJuego" element = {<ModificarJuego/>} />
		<Route path = "/PaginaGrupo" element = {<PaginaGrupo/>} />
		<Route path = "/MenuGrupo" element = {<MenuGrupo/>} />
		<Route path = "/ListadoGrupos" element = {<ListadoGrupos/>} />
		<Route path = "/ModificarGrupo" element = {<ModificarGrupo/>} />
		
		
		
		
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