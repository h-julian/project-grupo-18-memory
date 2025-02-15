const express = require('express');
const fs = require('fs');
const path = require('path');
const app = express();
const port = 3000;

app.use(express.json());
app.use(express.static('public'));

// Ruta para obtener las noticias
app.get('/api/noticias', (req, res) => {
    fs.readFile(path.join(__dirname, 'public', 'data', 'noticias.json'), 'utf8', (err, data) => {
        if (err) {
            return res.status(500).json({ error: 'Error al leer el archivo de noticias' });
        }
        res.json(JSON.parse(data));
    });
});

// Ruta para añadir una nueva noticia
app.post('/api/noticias', (req, res) => {
    const nuevaNoticia = req.body;
    fs.readFile(path.join(__dirname, 'public', 'data', 'noticias.json'), 'utf8', (err, data) => {
        if (err) {
            return res.status(500).json({ error: 'Error al leer el archivo de noticias' });
        }
        const noticias = JSON.parse(data);
        noticias.push(nuevaNoticia);
        fs.writeFile(path.join(__dirname, 'public', 'data', 'noticias.json'), JSON.stringify(noticias, null, 2), (err) => {
            if (err) {
                return res.status(500).json({ error: 'Error al escribir en el archivo de noticias' });
            }
            res.status(201).json(nuevaNoticia);
        });
    });
});

app.listen(port, () => {
    console.log(`Servidor escuchando en http://localhost:${port}`);
});