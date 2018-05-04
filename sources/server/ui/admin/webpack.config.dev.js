var path = require('path');
var params = require('./package.json');
var copyWebpackPlugin = require('copy-webpack-plugin');
var outputPath = '../../resources/admin/';

module.exports = {
    entry: {
        main: './src/scripts/main.js'
    },
    output: {
        path: path.resolve(__dirname, outputPath),
        filename: 'js/[name].bundle.js'
    },
    module: {
        loaders: [
            {
                test: /\.js$/,
                loader: 'babel-loader',
                include: path.resolve(__dirname, 'src'),
                exclude: [path.resolve(__dirname, 'node_modules'), path.resolve(__dirname, 'html')]
            }
        ]
    },
    plugins: [
        new copyWebpackPlugin([{
            from: path.resolve(__dirname, 'html/admin'),
            to: path.resolve(__dirname, outputPath),
            type: 'dir',
            transform(content) {
                content = content.toString();
                content = content.replace(/<%=\s*baseurl\s*%>/g, params.baseurl);
                content = content.replace(/<%=\s*basepath\s*%>/g, params.basepath);
                content = content.replace(/<%=\s*version\s*%>/g, params.version);
                return new Buffer(content, 'UTF-8');
            }
        }]),
        new copyWebpackPlugin([{
            from: path.resolve(__dirname, 'html/vendors'),
            to: path.resolve(__dirname, outputPath + 'vendors'),
            type: 'dir'
        }])
    ],
    devtool: 'cheap-module-eval-source-map'
};