var config = {
   entry: './main.js',
	
   output: {
      path:__dirname+'./',
      filename: 'index.js',
   },
	
   devServer: {
      inline: true,
      port: 9090
   },
	
   module: {
      loaders: [
         {
            test: /\.jsx?$/,
            exclude: /node_modules/,
            loader: 'babel-loader',
				
            query: {
               presets: ['es2016', 'react']
            }
         },
        { test: /\.css$/, loader: "style-loader!css-loader" }
      ]
   }
}

module.exports = config;