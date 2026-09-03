import { useState, useEffect } from 'react';
import { useNavigate } from "react-router";
import './modificarperfil.css'
import { API_URL } from '../../config';
import AlertMessage from '../../components/AlertMessage/AlertMessage';
import Footer from '../../components/Footer/Footer';


function Modificarperfil(){		
	
	
	
	
	const [estadofoto, setestadofoto] = useState(false)
	const [mostrarModalBaja, setMostrarModalBaja] = useState(false);
	const [nuevonombre, setnuevonombre] = useState("")
	const [alerta, setAlerta] = useState(null);
	const insertarnombre = (e) => {
		
		setnuevonombre(e.target.value)
		
		
	}
	
	
	const handleLogout = () => {
	    localStorage.removeItem('token');
	    window.location.href = '/login'; 
	  };
	  
	  const handleDarDeBaja = (e) => {
	  		e.preventDefault();
	  		setMostrarModalBaja(true);
	  	}
	
		const confirmarBaja = () => {
		        let token = localStorage.getItem('token');
				
				fetch(`${API_URL}/dardebaja`, {
					method: 'POST', 
					headers: {
						'Content-Type': 'application/json',
						'Authorization': `Bearer ${token}` 
					},
					body: JSON.stringify({}) 	
				})
				.then(res => {
		            if (!res.ok) {
		                return res.text().then(text => { throw new Error(text) });
		            }
		            return res.text();
		        })
		        .then(mensaje => {
		            setMostrarModalBaja(false); 
		            setAlerta({ tipo: 'ok', mensaje: "Usuario dado de baja correctamente." });
		            setTimeout(handleLogout, 2000);
		        })
		        .catch(err => {
		            setMostrarModalBaja(false); 
		            setAlerta({ tipo: 'error', mensaje: "Error al dar de baja." });
		            setTimeout(() => setAlerta(null), 5000);
		        });
		    }
	
	const enviar = (e) => {
		let token = localStorage.getItem('token');
		
		e.preventDefault();
		
		console.log(imagen)
		
		
		console.log("Token a enviar:", token);
		
		fetch(`${API_URL}/actualizardatosperfil`, {
			
			method: 'POST', 
			headers: {
			'Content-Type': 'application/json',
			'Authorization': 'Bearer ' + token },
			body: JSON.stringify({foto_perfil: imagen, nombre_usuario: nuevonombre}) 
			
			
		})
		
		.then(response => response.json())
		.then(res => {
					console.log("res:" + res);
		            if (res=='ok') {
		                return true;
		            }else{throw new Error("error");}
		            
		        })
		.then(() => {
	            
	            setAlerta({ tipo: 'ok', mensaje: "Perfil actualizado correctamente." });
				setnombreoriginal(nuevonombre);
	            setnuevonombre(""); 
	            setTimeout(() => setAlerta(null), 3000);
			})
			.catch(error => {
				console.error('Fallo en la petición:', error);
				setAlerta({ tipo: 'error', mensaje: "Error al intentar actualizar los datos." });
	            setTimeout(() => setAlerta(null), 5000);
		    });
	}
	
	const [imagen, setimagen] = useState("")
	const [nombreoriginal, setnombreoriginal] = useState("")
	
	
	
	
	
	useEffect(() => {
		let tokenActual = localStorage.getItem('token');
		
	
	fetch(`${API_URL}/fotousuario`, {
		
		method: 'POST', 
		  headers: {
		    'Content-Type': 'application/json',
			'Authorization': 'Bearer ' + tokenActual
		  },
		  body: JSON.stringify({}) 
		})
		
		.then(response => response.json())
		.then(data => {
			
			setimagen(data.foto_perfil)
			
			setnombreoriginal(data.nombre_usuario)
		    
		   
		})
		.catch(error => console.error('Error:', error));
		

		
		}, []);
		
	

		
		const navigate = useNavigate();
		
		
		const volver = () => {
			
			navigate("/login")
		}
		

		

				

			
			
			
		const insertarimagen = (e) => {
			
			let reader = new FileReader()
			reader.readAsDataURL(e.target.files[0])
			reader.onload = () => {
			setimagen(reader.result )
			setestadofoto(true);
			
			
			}
		}

			

		
		return(	
			<section>
			
			
			
			<div className="contendorprincipal">
			<h1 className='texto-modificar-perfil'> Modificar tu perfil </h1>
			<form onSubmit={enviar} className='form'>
			<p className='text'>Actualizar imagen</p>
			
			<input className='file-input' type = "file" 
						accept="image/*"
						onChange={insertarimagen}	
						/>
			<img className='imagen' style={{ width: '20vh', height: '20vh', objectFit: 'cover', borderRadius: '50%' }} src = {imagen} //lo pongo asi porque en el css no aplica los cambios no se qeu onda
						/>
			<p className='texto-nombre'>{nombreoriginal}</p>	
			<p className='text'>Cambiar nombre</p>			
			<input  className='input'
			placeholder="Ingrese nuevo nombre"
			value={nuevonombre}
			onChange={insertarnombre}
			
				/>			
			
			<button className='submit-btn' type="submit" disabled={!estadofoto && nuevonombre == ""} >Confirmar cambios  </button>
			<button type = "button"className='btn-dar-baja' onClick={handleDarDeBaja }> Dar de baja </button>
			<button type="button" className='btn-volver'onClick={volver}> Volver </button>
			
			
			{alerta !== null && (
			    <AlertMessage 
			        tipo={alerta.tipo} 
			        mensaje={alerta.mensaje} 
			        onClose={() => setAlerta(null)} 
			    />
			)}
			
			
			
			
			</form>
			
		</div>
		{mostrarModalBaja && (
		                <div className="modal-overlay">
		                    <div className="modal-content">
		                        <h3>Eliminar cuenta?</h3>
		                        <p>Esta accion es irreversible y se borraran todas tus reseñas.</p>
		                        <div className="modal-acciones">
		                            <button 
		                                className="btn-secundario" 
		                                onClick={() => setMostrarModalBaja(false)}
		                            >
		                                Cancelar
		                            </button>
		                            <button 
		                                className="btn-peligro" 
		                                onClick={confirmarBaja}
		                            >
		                                Confirmar
		                            </button>
		                        </div>
		                    </div>
		                </div>
		            )}
		<Footer/>
		</section>
		
		)
}



export default Modificarperfil;