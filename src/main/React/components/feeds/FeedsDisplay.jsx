import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

export default class FeedsDisplay extends React.Component {
    constructor(props) {
        super(props)
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this)
    }


    render() {
        var plateform = this.props.feed.get('plateform');
        return (
                <tr key={this.props.feed.get('feedByClientKey').get('uuid_feed')}>
                        <td>{this.props.feed.get('fromUser')}</td>
                        <td><a target="_blank" href={this.props.feed.get('link')}><strong>{this.props.feed.get('text_feed').substring(0,150)}</strong></a></td>
                        <td>{plateform}</td>
                        <td><img src={this.props.feed.get('image')} width={50} height={50} /></td>
                </tr>
        )

    }

}