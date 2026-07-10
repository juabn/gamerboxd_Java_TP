import { useState } from 'react';

import './companias.css'

function AdministrarCompanias(){
	
	
	
	const [nombrecompania, setnombrecompania] = useState("");
	
	return(
		<div className='ContenedorPrincipalCompanias'>
		
		
		
		<p className='textop'> Agregar compania</p>
		<div  className='contenedorsecundariocompania'>
		<input/>
		<p className='empresa'> {nombrecompania} </p>
		<button> Agregar</button>
		<p className='empresa'> Aca iria un select</p>
		<button> Borrar </button>
		
		<button> Modificar </button>
		</div>
		</div>
	)
	
	
}



export default AdministrarCompanias