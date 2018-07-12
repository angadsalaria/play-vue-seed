var CleanWebpackPlugin = require('clean-webpack-plugin')
var path = require('path')

module.exports.config = {

  plugins: [
    new CleanWebpackPlugin(['dist'])
  ],

  resolve: {

    alias: {
      Common: path.resolve(__dirname, 'src/common/')
    }
  }

}
