


import './Register.css'

function Hola(){
	
	
	const manejarClick = () => {
	    alert("¡Hiciste click en el párrafo!");
	  };
	
	return(
		
		<div className="contenedorPrincipal">
		
		
		
		<div className="primerDiv">
		
		<div className="divlogin">
		
		<div className='bordecolor'> </div>
		
		<p className='bienvenido'> Bienvenido </p>
		
		<div className='inputs'>
		<input className='input' type="text" placeholder='Ingrese su usuario'/>
		<input className='input' type="password" placeholder='Ingrese su contraseña' />
		
		</div>
		
		<div className='divboton'>
		<button className='ingresar'> Ingresar </button>
		</div>
		<p onClick={manejarClick} className='aviso'> ¿No tienes cuenta? </p>
		<p className='aviso'> ¿Olvidaste tu contraseña? </p>
		
		
		 </div>
		
		
		
		</div>
		
		
				
		
		
		
		</div>
	);
}

export default Hola;