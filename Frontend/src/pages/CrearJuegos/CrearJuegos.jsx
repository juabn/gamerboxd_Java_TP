import './CrearJuegos.css'
import { useState, useEffect } from 'react';
import Select from 'react-select'

function CrearJuegos(){
	
	const [nombrejuego, setnombrejuegop] = useState("");
	const [descripcion, setdescripcion] = useState("");
	const [imagen, setimagen] = useState("");
	
	const [listaEmpresas, setListaEmpresas] = useState([])
	const [companiasElegidas, setCompaniasElegidas] = useState([]);

	
	//lista de empresas solo para probar


	
	
	useEffect(() => {
			
		  fetch('http://localhost:8081/listaempresas')
		  
		    .then((Response) => Response.json())
		    .then((dataa) => {
			  const nuevoarray = dataa.map(empresa => ({
			  	
			  	value: empresa.id,
			  	label: empresa.name
			  		
			  }));
			  
			 
			  setListaEmpresas( nuevoarray);
			  
		      
		    })
		    .catch((error) => console.error("Error cargando empresas:", error));
		}, []);
		
		
		const manejarCambioOpcion = (opcionesSeleccionadas) => {
		    
		    setCompaniasElegidas(opcionesSeleccionadas || []);
		    
		    
		    console.log(opcionesSeleccionadas)
		}
	
	
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

		

	

	
		const enviar = (e) => {
		    let token = localStorage.getItem('token');
		    e.preventDefault();
		    
		    fetch('http://localhost:8081/crearPropuesta', {
		        method: 'POST', 
		        headers: {
		            'Content-Type': 'application/json',
		            'Authorization': 'Bearer ' + token 
		        },
		        body: JSON.stringify({
		            nombrejuego: nombrejuego, 
		            descripcionjuego: descripcion,
		            foto: imagen,
		            companiasJuego: companiasElegidas.map(empresa => ({
		                id: empresa.value,
		                name: empresa.label
		            })) 
		        }) 
		    }) 
		    .then(response => {
		        if (response.status === 200) {
		            alert("Solicitud enviada correctamente");
		        } 
		        else if (response.status === 402) {
		            alert("Este juego ya existe");
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
		
		<Select
		isMulti
		  
		  options={listaEmpresas}
		  onChange={manejarCambioOpcion}
		/>
		
		
		
		<button type='submit'>Enviar</button>
		
		</form>
		
		</div>
		
		
	)
	
	
	
	
	
}

export default CrearJuegos