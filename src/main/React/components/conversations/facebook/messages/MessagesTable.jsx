import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'
import MessagesForm from './MessagesForm'

export default class MessagesTable extends React.Component {
    constructor(props) {
        super(props)
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this)
    }


    render() {
        return  (
            <div>
                <h1>Messages</h1>
                <table className="table table-stripped table-bordered">
                    <thead>
                    <tr>
                        <th>User</th>
                        <th>Message</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.props.messages.map(message =>
                            <tr key={message.get('id')}>
                                <td>
                                    <img src={"http://graph.facebook.com/" + message.get('from').get('id') + "/picture?type=square"} />
                                </td>
                                <td>
                                    {message.get('message')}
                                </td>
                            </tr>
                        )
                    }

                    </tbody>
                </table>
                <MessagesForm sendMessage = {this.props.sendMessage} conversationId = {this.props.conversationId}/>
            </div>
        );
    }
}
