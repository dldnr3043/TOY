// module.exports = {
//     transpileDependencies: [/node_modules[/\\]mdbvue[/\\]/]  ,
//     devServer: {
//         host : '127.0.0.1',
//         port : 50000
//     }
                
// }

const ansiRegex = require('ansi-regex')
var path = require("path") 
module.exports = { 
    outputDir : path.resolve(__dirname, "./build/public/"), 
    devServer: { 
        port: 3000,
        proxy: 'http://localhost:8080'
    },
   
}


