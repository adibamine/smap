    import React from 'react'
    import PureRenderMixin from 'react-addons-pure-render-mixin'
    import {ComposedChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend} from 'recharts';

    export default class ChartDisplay extends React.Component {
        constructor(props) {
            super(props)
            this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this)
        }


        render() {
            return <ComposedChart width={1000} height={400} data={this.props.chartData}
                                         margin={{top: 20, right: 20, bottom: 20, left: 20}}>
                <XAxis dataKey="end_time"/>
                <YAxis />
                <Tooltip/>
                <Legend/>
                <CartesianGrid stroke='#f5f5f5'/>
                <Bar dataKey='value' barSize={20} fill='#9354E9'/>
            </ComposedChart>
        }

    }