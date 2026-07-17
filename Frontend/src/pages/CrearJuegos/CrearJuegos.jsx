import './CrearJuegos.css'
import { useState } from 'react';

function CrearJuegos(){
	
	const [nombrejuego, setnombrejuegop] = useState("");
	const [descripcion, setdescripcion] = useState("");
	const [imagen, setimagen] = useState("");
	
	const manejarnombrejuego = (e) => {
			
			setnombrejuegop(e.target.value)
		};
		
		
	const manejardescripcion = (e) => {
			
			setdescripcion(e.target.value)
		};
		
		
		
		const insertarimagen = (e) => {
			
			let reader = new FileReader()
			reader.readAsDataURL(e.target.files[0])
			reader.onload = () => {
			setimagen(reader.result )
			
			}
		}

		

	
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
		.then(response => {
			        
		        if (response.status === 200) {
		            alert("Solicitud enviada correctamente");
		        } 
				else if (response.status === 402) {
		            alert("Esta juego ya existe");
					        
		        }
				else {
		            alert("Hubo un problema al crear la empresa: " + response.status);
		        }
		    })
			    .catch(error => console.error('Error en el fetch:', error));
		
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
		
		
		<input 
		type = "file" 
		accept="image/*"
		onChange={insertarimagen}	
		/>
		<img className='imagen' style={{ width: '20vh', height: '20vh', objectFit: 'cover', borderRadius: '50%' }} src = {imagen} //lo pongo asi porque en el css no aplica los cambios no se qeu onda
		/>
		
		
		
		
		<button type='submit'>Enviar</button>
		
		</form>
		
		</div>
		
		
	)
	
	
	
	
	
}

export default CrearJuegos