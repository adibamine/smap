import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

export default class FeedsDisplay extends React.Component {
    constructor(props) {
        super(props)
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this)
    }


    render() {
        return (
            <tr key={this.props.monitoring_feed.get('link')}>
                <td>{this.props.monitoring_feed.get('title')}</td>
                <td>
                    <a target="_blank" href={"localhost:9090/api/dataweb?link=" + this.props.monitoring_feed.get('link') +"&lang="+this.props.monitoring_feed.get('lang')}><strong>Article</strong></a>
                </td>
            </tr>
        )

    }

}