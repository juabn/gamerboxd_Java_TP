import { useState } from 'react';
import { useNavigate } from "react-router-dom"; 
import { API_URL } from '../../config';
import Footer from '../../components/Footer/Footer';
import AlertMessage from '../../components/AlertMessage/AlertMessage';

import './companias.css'

function AdministrarCompanias(){
	
	const navigate = useNavigate();
	
	const [nombrecompania, setnombrecompania] = useState("");
    const [alerta, setAlerta] = useState(null);
	
	const manejarnombrecompania = (e) => {
		setnombrecompania(e.target.value)
	};
	
	const modificarcompania = (e) => {
		e.preventDefault();
		let token = localStorage.getItem('token');
        
        if (!nombrecompania.trim()) {
            setAlerta({ tipo: 'error', mensaje: "Por favor, ingresa el nombre de la compañia para modificar." });
            setTimeout(() => setAlerta(null), 4000);
            return;
        }
				
		fetch(`${API_URL}/existeempresa`,{
			method: 'POST', 
			headers: {
			    'Content-Type': 'application/json',
			    'Authorization': 'Bearer ' + token 
            },
			body: JSON.stringify({name: nombrecompania})
		})
		.then(response => {
	        if (response.status === 200) {
                setAlerta({ tipo: 'ok', mensaje: "Empresa encontrada. Redirigiendo..." });
                setTimeout(() => {
                    setnombrecompania(""); 
				    navigate("/Modificarcompanias", {state:{nombre: nombrecompania}});
                }, 1500);
	        } 
			else if (response.status === 404) {
                setAlerta({ tipo: 'error', mensaje: "No existe esa empresa en la base de datos." });
	            setnombrecompania(""); 
                setTimeout(() => setAlerta(null), 4000);
	        }
			else if (response.status === 401 || response.status === 402) {
                setAlerta({ tipo: 'error', mensaje: "Error de autorizacion o token invalido." });
	            setnombrecompania(""); 
                setTimeout(() => setAlerta(null), 4000);
	        }
			else {
                setAlerta({ tipo: 'error', mensaje: "Error en la base de datos, intente mas tarde." });
                setTimeout(() => setAlerta(null), 4000);
	        }
	    })
	    .catch(error => {
            console.error('Error:', error);
            setAlerta({ tipo: 'error', mensaje: "Error de conexion, intente mas tarde." });
            setTimeout(() => setAlerta(null), 4000);
        });
	};
	
	const crearcompania = (e) => {
		e.preventDefault();
		let token = localStorage.getItem('token');
		
        if (!nombrecompania.trim()) {
            setAlerta({ tipo: 'error', mensaje: "Ingresa el nombre de la compañía para agregar." });
            setTimeout(() => setAlerta(null), 4000);
            return;
        }

	    fetch(`${API_URL}/crearcompania`,{
            method: 'POST', 
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token 
            },
            body: JSON.stringify({name: nombrecompania})
	    })
	    .then(response => {
	        if (response.status === 200) {
                setAlerta({ tipo: 'ok', mensaje: "¡Compañia agregada correctamente!" });
	            setnombrecompania(""); 
                setTimeout(() => setAlerta(null), 3000);
	        } 
			else if (response.status === 409) {
                setAlerta({ tipo: 'error', mensaje: "Esa empresa ya se encuentra registrada." });
	            setnombrecompania(""); 
                setTimeout(() => setAlerta(null), 4000);
	        }
			else {
                setAlerta({ tipo: 'error', mensaje: "Hubo un problema al crear la empresa." });
                setTimeout(() => setAlerta(null), 4000);
	        }
	    })
	    .catch(error => {
            console.error('Error en el fetch:', error);
            setAlerta({ tipo: 'error', mensaje: "Error en la conexión." });
            setTimeout(() => setAlerta(null), 4000);
        });
	};

    const volver = () => {
        navigate(-1); 
    };
	
	return(
        <section className="pagina-companias">
            
            {alerta !== null && (
                <div className="companias-alerta-wrapper">
                    <AlertMessage 
                        tipo={alerta.tipo} 
                        mensaje={alerta.mensaje} 
                        onClose={() => setAlerta(null)} 
                    />
                </div>
            )}

            <div className='companias-container'>
                <div className="companias-card-glass">
                    
                    <div className="companias-header">
                        <h2 className="companias-titulo">Administrar Compañias</h2>
                        <p className="companias-subtitulo">Agregá nuevas empresas o modifica las existentes.</p>
                    </div>
                    
                    <form className='companias-form'>
                        <div className="companias-campo">
                            <label className="companias-label">Nombre de la compañia</label>
                            <input 
                                className="companias-input"
                                type="text"
                                placeholder="Ej: Rockstar Games"
                                value={nombrecompania}
                                onChange={manejarnombrecompania}
                                required
                            />
                        </div>
                        
                        <div className="companias-acciones">
                            <button className="btn-primario" type="button" onClick={crearcompania}>Agregar Compañia</button>
                            <button className="btn-secundario" type="button" onClick={modificarcompania}>Modificar Compañia</button>
                            <button className="btn-peligro" type="button" onClick={volver}>Volver</button>
                        </div>
                    </form>
                </div>
            </div>

            <Footer />
        </section>
	)
}

export default AdministrarCompanias;