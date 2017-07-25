import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'
import {PieChart, Pie, Tooltip, Cell} from 'recharts';

export default class InsightsDisplay extends React.Component {
    constructor(props) {
        super(props)
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this)
        this.COLORS = ['#0088FE', '#C40006', '#FFBB28'];
        this.COLORS2 = ['#FE0067', '#00C42C', '#FF5700'];
    }


    render() {
        return (
            <div>
                <PieChart width={1000} height={400}>
                    <Pie isAnimationActive={false} data={this.props.bySentiments} cx={200} cy={200} outerRadius={80} fill="#8884d8" label>
                        {
                            this.props.bySentiments.map((entry, index) => <Cell key={entry} fill={this.COLORS[index % this.COLORS.length]}/>)
                        }
                    </Pie>
                    <Pie data={this.props.byLanguages} cx={500} cy={200} innerRadius={40} outerRadius={80} fill="#82ca9d">
                        {
                            this.props.byLanguages.map((entry, index) => <Cell key={entry} fill={this.COLORS2[index % this.COLORS2.length]}/>)
                        }
                    </Pie>
                    <Tooltip/>
                </PieChart>
                <p className="notes">Schema1: Nombre de commentaires positifs, négatifs, autres !</p>
                <p className="notes">Schema2: Nombre de commentaires par langue !</p>
            </div>
        );
    }

}