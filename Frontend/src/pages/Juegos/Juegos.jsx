import './Juegos.css'
import Select from 'react-select'

import { useState, useEffect } from 'react';
import GameCard from '../../components/GameCard/GameCard'

function Juegos(){
	
	const [listaJuegos, setListaJuegos] = useState([])
	
	const [listaEmpresas, setListaEmpresas] = useState([])
	
	
	
	useEffect(() => {
		
	  fetch('http://localhost:8081/listaempresas')
	  
	    .then((Response) => Response.json())
	    .then((dataa) => {
		  const nuevoarray = dataa.map(empresa => ({
		  	
		  	value: empresa.id,
		  	label: empresa.name
		  		
		  }));
		  
		  const opcionPorDefecto = { value: '', label: 'Todos' };
		  setListaEmpresas( [opcionPorDefecto, ...nuevoarray]);
		  
	      
	    })
	    .catch((error) => console.error("Error cargando empresas:", error));
	}, []);
	
	useEffect(() =>{
		
		fetch('http://localhost:8081/listajuegos')
		.then((response) => response.json())
		.then((data) => {
		       
		        setListaJuegos(data);
		    })
			
			.catch((error) => console.error("Error cargando juegos:", error));
		
	}, [])
	
	//usestate para saber en base a que compania hacer el filtro 
		const [companiaelegida, seleccionarcompaniaelegida] = useState('Todos');
	
	const manejarCambioOpcion = (event) => {
		
		
		seleccionarcompaniaelegida(event.label)
	}
	


	const juegosnorepetidos = []
	const juegosExistente = []
	
	for (let i = 0; i<listaJuegos.length; i++){
		
		if(!juegosExistente.includes(listaJuegos[i].name)) {
			juegosExistente.unshift(listaJuegos[i].name)
			juegosnorepetidos.unshift(listaJuegos[i])
		
	}else{
		
		console.log("asd")
	}
	}
	
	
	var array_filtro = [];

	for (let i = 0; i < listaJuegos.length; i++){
		
		
		if(listaJuegos[i].developers.includes(companiaelegida)){
			
			array_filtro.unshift(listaJuegos[i])
		}
		
		if(companiaelegida == "Todos"){
			
			array_filtro = listaJuegos;
			
		}
		
		
		
	}


	
	
	return(
		<div>
		<div>
		<p className="titulo"> Elige tu juego bla bla bla </p>
		</div>
		
		<div className="filtros">
		<Select
		
		defaultValue={{ value: 1, label: 'Todos' }}
		options = {listaEmpresas}
		onChange={manejarCambioOpcion}
		
		 />
		<div className="catalogo-juegos">
		{array_filtro.map((juego) => (
		          <GameCard 
		            key={juego.id}         
		            titulo={juego.name}   
		            imagen={juego.background_image}   
		          />
		        ))}
		      </div>

		</div>
		
		</div>

		
	)	
}

export default Juegos;