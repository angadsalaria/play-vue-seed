var CleanWebpackPlugin = require('clean-webpack-plugin')

module.exports.config = {

  plugins: [
    new CleanWebpackPlugin(['dist'])
  ]

}
