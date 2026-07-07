import './Juegos.css'

import { useState, useEffect } from 'react';
import GameCard from '../../components/GameCard/GameCard'

function Juegos(){
	
	const [listaJuegos, setListaJuegos] = useState([])
	
	
	useEffect(() =>{
		
		fetch('http://localhost:8081/listajuegos')
		.then((response) => response.json())
		.then((data) => {
		       
		        setListaJuegos(data);
		    })
			
			.catch((error) => console.error("Error cargando juegos:", error));
		
	}, [])
	
	
	return(
		<div>
		<div>
		<p className="titulo"> Elige tu juego bla bla bla </p>
		</div>
		
		<div className="filtros">
		<select className='selector'><option value="compnia">Compañia</option></select>
		<select className='selector'><option value="Estrellas">Puntaje</option></select>
		<div className="catalogo-juegos">
		{listaJuegos.map((juego) => (
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