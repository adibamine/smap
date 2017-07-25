import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'
import FeedsDisplay from './FeedsDisplay'

export default class TermTable extends React.Component {
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
                <th>Username</th>
                <th>Feed Text</th>
                <th>Plateform</th>
                <th>Image</th>
            </tr>
            </thead>
            <tbody>
            {
                this.props.feeds.map(
                    feed => <FeedsDisplay feed={feed} />
                )
            }
            </tbody>
        </table>

    }

}