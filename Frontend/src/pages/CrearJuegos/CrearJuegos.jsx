import './CrearJuegos.css'
import { useState } from 'react';

function CrearJuegos(){
	
	const [nombrejuego, setnombrejuegop] = useState("");
	const [descripcion, setdescripcion] = useState("");
	const [imagen, setimagen] = useState("a");
	
	const manejarnombrejuego = (e) => {
			
			setnombrejuegop(e.target.value)
		};
		
		
	const manejardescripcion = (e) => {
			
			setdescripcion(e.target.value)
		};
		

	
	//lista de empresas solo para probar
	const listacompanias = [
	    { id: 4, name: "Rockstar Games", estado: "Activo" },
	    { id: 10, name: "Nintendo", estado: "Activo" }
	];
	
	
	const enviar = (e) => {
		
		

		let token = localStorage.getItem('token');
				
		e.preventDefault();
		
		fetch('http://localhost:8081/crearPropuesta', {
			
			
						
		method: 'POST', 
		headers: {
		'Content-Type': 'application/json',
		'Authorization': 'Bearer ' + token },
		body: JSON.stringify({
			nombrejuego: nombrejuego, 
			descripcionjuego: descripcion,
			foto: imagen,
			companiasJuego: listacompanias})
		
			
		})
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	return(
		
		
		<div className='divprincipalcreajuego'>
		
		<form onSubmit={enviar}>
		
		<p className="textocreajuegos"> nombre</p>
		<input
		onChange={manejarnombrejuego} />
		<p className="textocreajuegos"> descripcion</p>
		<input
		onChange={manejardescripcion} />
		<p className="textocreajuegos"> En estre espacio pondria un selector para la empresa</p>
		<p className="textocreajuegos">Imagen</p>
		]
		<input />
		<button type='submit'>Enviar</button>
		
		</form>
		
		</div>
		
		
	)
	
	
	
	
	
}

export default CrearJuegos