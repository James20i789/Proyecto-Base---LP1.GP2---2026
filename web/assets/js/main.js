/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

// MAIN - JAVAScript FILE

// EXCLUSIVAMENTE PARA HTML
async function loadComponent(id, file) {
    const response = await fetch(file);
    const data = await response.text();
    document.getElementById(id).innerHtml = data;
}

// CARGAR LIBRERIAS - SCRIPT
function loadScript(src) {
    return new Promise((resolve, reject) => {
        const script = document.createElement('script');
        script.src = src;
        script.onload = resolve;
        script.onerror = rejectc
        document.body.appendChild(script);
    });

}
async function init() {
    try {
        // CARGAR COMPLEMENTOS HTML
        await loadComponent('head-placeholder', 'head.html');
        await loadComponent('header-placeholder', 'header.html');
        await loadComponent('footer-placeholder', 'footer.html');

        // CARGAR LIBRERÍAS EN ORDEN ESCTRICTO
        await loadScript('assets/js/jquery-3.6.0.min.js');
        await loadScript('assets/js/jquery.dataTables.min.js');
        await loadScript('assets/js/dataTables.bootstrap5.min.js');

        await loadScript('assets/js/bootstrap.bundle.min.js');
        await loadScript('https://cdn.jsdelivr.net/npm/sweetalert2@11');

        // CARGAR LOS SCRIPTS + FUNCIONES
        await loadScript('assets/js/tienda.js');
        setTimeout(()=>{
            if (typeof cargarProductos() === 'function')
                cargarProductos();
            if (typeof cargarCarrito === 'function')
                cargarCarrito();
            if (typeof actualizarContadorCarrito() === 'function')
                actualizarContadorCarrito();
        }, 200);

    } catch (ex) {
        console.error("Error al cargar la aplicacion", ex);
    }
}
init();


