var webpack = require('./vue.webpack.config.js')

module.exports = {

  // Merged into the final Webpack config
  configureWebpack: webpack.config,

  pages: {
    index: {
      entry: 'src/pages/app/main.js',
      template: 'static/index.html',
      filename: 'index.html'
    },
    todo: {
      entry: 'src/pages/todo/main.js',
      template: 'static/index.html',
      filename: 'todo.html'
    }
  },


  outputDir: '../public',

  devServer: {
    port: 8080,
    open: true
  }

}
