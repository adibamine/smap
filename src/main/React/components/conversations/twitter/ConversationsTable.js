import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

export default class TwitterConversationsTable extends React.Component {
    constructor(props) {
        super(props)
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this)
    }


    render() {
        console.log(this.props.twitterConversations.map(conversation => console.log(conversation)))
        return  (
            <div>
                <h1>Inbox</h1>
                <table className="table table-stripped table-bordered">
                    <thead>
                    <tr>
                        <th>User</th>
                        <th>Message</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.props.twitterConversations.map(conversation =>
                            <tr key={conversation}>
                                <td>
                                    {conversation.get('text')}
                                </td>
                                <td>
                                    ok
                                </td>
                            </tr>
                        )
                    }

                    </tbody>
                </table>
            </div>
        );
    }
}
