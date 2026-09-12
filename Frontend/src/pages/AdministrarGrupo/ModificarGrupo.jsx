import { useState } from 'react';
import { useNavigate } from 'react-router';
import { useLocation } from 'react-router-dom';
import { API_URL } from '../../config';
import "./ModificarGrupo.css";
import Footer from '../../components/Footer/Footer';
import AlertMessage from '../../components/AlertMessage/AlertMessage'; 

function ModificarGrupo() {
    const location = useLocation();
    const navigate = useNavigate();

    const idgrupo = location.state?.id || '';

    const [fotoActual, setFotoActual] = useState(location.state?.img || '');
    const [nombreActual, setNombreActual] = useState(location.state?.nombre || '');
    const [nuevonombre, setnuevonombre] = useState("");
    const [descripcion, setdescripcion] = useState("");
    const [nuevaImagen, setNuevaImagen] = useState("");
    const [estadofoto, setestadofoto] = useState(false);

    const [alerta, setAlerta] = useState(null);

    const insertarnombre = (e) => setnuevonombre(e.target.value);
    const insertardescripcion = (e) => setdescripcion(e.target.value);

    const handleLogout = () => {
        navigate("/");
    };

    const dardebaja = (e) => {
        let token = localStorage.getItem('token');
        e.preventDefault();

        fetch(`${API_URL}/dardebajagrupo`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            body: JSON.stringify({ id: idgrupo })
        })
        .then(response => {
            if (response.status === 200) {
                setAlerta({ tipo: 'ok', mensaje: "Grupo dado de baja correctamente" });
                
                setTimeout(() => handleLogout(), 2000);
            } else if (response.status === 401) {
                setAlerta({ tipo: 'error', mensaje: "Error en la bd, intente nuevamente más tarde" });
                setTimeout(() => setAlerta(null), 5000);
            }
        })
        .catch(() => {
            setAlerta({ tipo: 'error', mensaje: "Error inesperado, intente nuevamente más tarde" });
            setTimeout(() => setAlerta(null), 5000);
        });
    };

    const enviar = (e) => {
        let token = localStorage.getItem('token');
        e.preventDefault();

        fetch(`${API_URL}/actualizardatosgrupo`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            body: JSON.stringify({
                id: idgrupo,
                foto_perfil: nuevaImagen,
                nombre: nuevonombre,
                descripcion: descripcion
            })
        })
        .then(response => {
            if (response.status === 200) {
                setAlerta({ tipo: 'ok', mensaje: "Actualización realizada con éxito" });
                setTimeout(() => setAlerta(null), 3500);

                if (nuevonombre.trim() !== "") {
                    setNombreActual(nuevonombre);
                    setnuevonombre("");
                }
                if (nuevaImagen !== "") {
                    setFotoActual(nuevaImagen);
                    setNuevaImagen("");
                    setestadofoto(false);
                }
                setdescripcion("");
            } else if (response.status === 409) {
                setAlerta({ tipo: 'error', mensaje: "Ya existe un grupo con ese nombre" });
                setTimeout(() => setAlerta(null), 5000);
            } else {
                setAlerta({ tipo: 'error', mensaje: "Error en la conexión, pruebe más tarde" });
                setTimeout(() => setAlerta(null), 5000);
            }
        })
        .catch(error => {
            console.error("El error real es:", error);
            setAlerta({ tipo: 'error', mensaje: "Error en la conexión con la base de datos, pruebe más tarde" });
            setTimeout(() => setAlerta(null), 5000);
        });
    };

    const volver = () => {
        navigate("/login");
    };

    const insertarimagen = (e) => {
        if (e.target.files && e.target.files[0]) {
            let reader = new FileReader();
            reader.readAsDataURL(e.target.files[0]);
            reader.onload = () => {
                setNuevaImagen(reader.result);
                setestadofoto(true);
            };
        }
    };

    return (
		<section style={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
            
            
            
            <div className="contendorprincipal" style={{ flex: 1 }}>
			
                <form onSubmit={enviar} className='form'>
                    <p className='text'>Actualizar imagen</p>

                    <input
                        className='file-input'
                        type="file"
                        accept="image/*"
                        onChange={insertarimagen}
                    />

                    <img
                        className='imagen'
                        style={{ width: '20vh', height: '20vh', objectFit: 'cover', borderRadius: '50%' }}
                        src={nuevaImagen || fotoActual}
                        alt="Foto del grupo"
                    />
                    
                    <p className='text'>{nombreActual}</p>

                    <p className='text'>Cambiar nombre del grupo</p>
                    <input
                        className='input'
                        placeholder="Ingrese nuevo nombre"
                        value={nuevonombre}
                        onChange={insertarnombre}
                    />

                    <p className='text'>Cambiar descripción</p>
                    <input
                        className='input'
                        placeholder="Ingrese nueva descripción"
                        value={descripcion}
                        onChange={insertardescripcion}
                    />

                    <button
                        className='submit-btn'
                        type="submit"
                        disabled={!estadofoto && nuevonombre.trim() === "" && descripcion.trim() === ""}
                    >
                        Confirmar cambios
                    </button>
                    <button type="button" className="botonDarBaja" onClick={dardebaja}>Dar de baja grupo</button>
                    <button type="button" className="botonVolver" onClick={volver}>Volver</button>
                </form>
				
				{alerta !== null && (
                <div className="grupo-alerta-wrapper">
                    <AlertMessage 
                        tipo={alerta.tipo} 
                        mensaje={alerta.mensaje} 
                        onClose={() => setAlerta(null)} 
                    />
                </div>
            )}

            </div>
		    <Footer/>
		</section>
    );
}

export default ModificarGrupo;