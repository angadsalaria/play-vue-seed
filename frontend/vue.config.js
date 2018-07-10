var webpack = require('./vue.webpack.config.js')

module.exports = {

  // Merged into the final Webpack config
  configureWebpack: webpack.config,

  chainWebpack: config => {
    config
      .plugin('html')
      .tap(args => {
        args[0].template = './static/index.html'
        return args
      })
  },

  outputDir: '../public',

  devServer: {
    port: 8080,
    open: true
  }

}
