var CleanWebpackPlugin = require('clean-webpack-plugin')
var OpenBrowserPlugin = require('open-browser-webpack-plugin')

module.exports.config = {

  plugins: [
    new CleanWebpackPlugin(['dist'])
  ]

}
