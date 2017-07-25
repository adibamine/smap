import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'
import MonitoringDisplay from './MonitoringDisplay'

export default class MonitoringTable extends React.Component {
    constructor(props) {
        super(props)
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this)
    }

    componentDidMount() {
    }

    render() {
        return <table className="table table-stripped table-bordered">
            <thead>
            <tr>
                <th>Titre</th>
                <th>Lien</th>
            </tr>
            </thead>
            <tbody>
            {
                this.props.monitoring_feeds.map(
                    monitoring_feed => <MonitoringDisplay monitoring_feed={monitoring_feed} />
                )
            }
            </tbody>
        </table>

    }

}